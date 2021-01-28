import { Component, OnInit } from '@angular/core';
import {CulturalOfferTypeService} from '../../services/cultural-offer-type.service';
import {CulturalOfferService} from '../../services/cultural-offer.service';
import {UserService} from '../../services/user.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-cultural-offers',
  templateUrl: './cultural-offers.component.html',
  styleUrls: ['./cultural-offers.component.scss']
})
export class CulturalOffersComponent implements OnInit {
    culturalOffers: Array<any>;


    constructor(
        private culturalOfferService: CulturalOfferService,
        private userService: UserService,
        private toastr: ToastrService
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

  subscribeUser(offerId: number): void {
        this.userService.subscribe(offerId).subscribe(
            result => {
                this.toastr.success('New subscription added successfully!');
            },
            error => {
                this.toastr.error('Already subscribed!');
            }
        );
  }



}
