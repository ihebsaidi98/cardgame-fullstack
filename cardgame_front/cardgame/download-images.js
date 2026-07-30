const fs = require('fs');
const https = require('https');
const path = require('path');

// Configuration
const suits = ['clubs', 'diamonds', 'hearts', 'spades'];
const ranks = ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'jack', 'queen', 'king', 'ace'];
const baseUrl = 'https://raw.githubusercontent.com/hayeah/playing-cards-assets/master/png/';
const destDir = path.join(__dirname, 'src', 'assets', 'img');

// Créer le dossier
if (!fs.existsSync(destDir)) {
    fs.mkdirSync(destDir, { recursive: true });
    console.log(`📁 Dossier créé : ${destDir}`);
}

let total = 52;
let downloaded = 0;
let errors = 0;

console.log('🚀 Téléchargement des 52 cartes...\n');

ranks.forEach(rank => {
    suits.forEach(suit => {
        const filename = `${rank}_of_${suit}.png`;
        const url = baseUrl + filename;
        const filePath = path.join(destDir, filename);

        const file = fs.createWriteStream(filePath);
        file.on('error', (err) => {
            console.error(`❌ Erreur fichier pour ${filename} : ${err.message}`);
            errors++;
            file.close();
        });

        https.get(url, (response) => {
            // Redirection (certains serveurs peuvent rediriger)
            if (response.statusCode === 301 || response.statusCode === 302) {
                const redirectUrl = response.headers.location;
                https.get(redirectUrl, (redirectResponse) => {
                    redirectResponse.pipe(file);
                    file.on('finish', () => {
                        file.close();
                        downloaded++;
                        console.log(`✅ ${filename} (${downloaded}/${total})`);
                        if (downloaded + errors === total) {
                            console.log('\n🎉 Téléchargement terminé !');
                            if (errors > 0) console.log(`⚠️ ${errors} fichiers ont échoué.`);
                        }
                    });
                }).on('error', (err) => {
                    console.error(`❌ Erreur redirection ${filename} : ${err.message}`);
                    errors++;
                    file.close();
                    fs.unlinkSync(filePath);
                });
                return;
            }

            if (response.statusCode === 200) {
                response.pipe(file);
                file.on('finish', () => {
                    file.close();
                    downloaded++;
                    console.log(`✅ ${filename} (${downloaded}/${total})`);
                    if (downloaded + errors === total) {
                        console.log('\n🎉 Téléchargement terminé !');
                        if (errors > 0) console.log(`⚠️ ${errors} fichiers ont échoué.`);
                    }
                });
            } else {
                console.log(`❌ ${filename} introuvable (code ${response.statusCode})`);
                errors++;
                file.close();
                fs.unlinkSync(filePath);
            }
        }).on('error', (err) => {
            console.error(`❌ Erreur réseau pour ${filename} : ${err.message}`);
            errors++;
            file.close();
            fs.unlinkSync(filePath);
        });
    });
});