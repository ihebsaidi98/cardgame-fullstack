import { Component, OnInit } from '@angular/core';
import { CardService } from '../../services/card.service';
import { HandResponse } from '../../models/hand-response.model';
import { Card } from '../../models/card.model';

@Component({
  selector: 'app-card-list',
  templateUrl: './card-list.component.html',
  styleUrls: ['./card-list.component.css']
})
export class CardListComponent implements OnInit {

  handResponse: HandResponse | null = null;
  loading = false;
  error: string | null = null;

  constructor(private cardService: CardService) { }

  ngOnInit(): void {
    this.loadCards();
  }

  loadCards(): void {
    this.loading = true;
    this.error = null;
    this.cardService.getRandomHand().subscribe({
      next: (data) => {
        this.handResponse = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Erreur API :', err);
        this.error = 'Impossible de charger les cartes. Vérifiez que le backend est en cours d\'exécution.';
        this.loading = false;
      }
    });
  }

  getImagePath(card: Card): string {
    return `assets/img/${card.imageName}`;
  }

  isRedSuit(suitName: string): boolean {
    return suitName === 'Cœur' || suitName === 'Carreau';
  }
}