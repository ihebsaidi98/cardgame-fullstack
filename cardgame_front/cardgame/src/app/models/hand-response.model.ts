import { Card } from './card.model';

export interface HandResponse {
  fullPack: Card[];
  randomHand: Card[];
  sortedHand: Card[];
}