import { Component, OnInit } from '@angular/core';
import {CulturalOfferService} from '../../services/cultural-offer.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-cultural-offer-details',
  templateUrl: './cultural-offer-details.component.html',
  styleUrls: ['./cultural-offer-details.component.scss']
})
export class CulturalOfferDetailsComponent implements OnInit {
    culturalOffer: any;
    rate: number;
    ratings: any;

  constructor(
      private route: ActivatedRoute,
      private culturalOfferService: CulturalOfferService
  ) {
      this.rate = 0;
      this.ratings = [];
  }

  ngOnInit(): void {
      this.route.params.subscribe(params => {
          this.culturalOfferService.getCulturalOffer(params.offerId).subscribe(
              result => {
                  this.culturalOffer = result;
                  this.ratings = result.ratings;
                  if (this.culturalOffer.ratings[0]) {
                      this.rate = result.ratings[0].ratingValue;
                  }
              },
              error => {
                  console.log(error.error);
              }
          );
      });
  }

}
