import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HandResponse } from '../models/hand-response.model';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class CardService {

  private apiUrl = `${environment.apiUrl}/random-hand-cards`;

  constructor(private http: HttpClient) { }

  getRandomHand(): Observable<HandResponse> {
    return this.http.get<HandResponse>(this.apiUrl);
  }
}