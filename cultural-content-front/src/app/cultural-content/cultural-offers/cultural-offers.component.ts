import { Component, OnInit } from '@angular/core';
import {CulturalOfferTypeService} from '../../services/cultural-offer-type.service';
import {CulturalOfferService} from '../../services/cultural-offer.service';

@Component({
  selector: 'app-cultural-offers',
  templateUrl: './cultural-offers.component.html',
  styleUrls: ['./cultural-offers.component.scss']
})
export class CulturalOffersComponent implements OnInit {
    culturalOffers: Array<any>;


    constructor(
        private culturalOfferService: CulturalOfferService
    ) {
        this.culturalOffers = [];
    }

  ngOnInit(): void {
        this.culturalOfferService.getCulturalOffers().subscribe(
            result => {
                this.culturalOffers = result;
            }, error => {
                console.log(error.error);
            }
        );
  }





}
