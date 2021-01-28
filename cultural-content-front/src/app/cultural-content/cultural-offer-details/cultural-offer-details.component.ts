import { Component, OnInit } from '@angular/core';
import {CulturalOfferService} from '../../services/cultural-offer.service';
import {ActivatedRoute} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {RatingService} from '../../services/rating.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-cultural-offer-details',
  templateUrl: './cultural-offer-details.component.html',
  styleUrls: ['./cultural-offer-details.component.scss']
})
export class CulturalOfferDetailsComponent implements OnInit {
    culturalOffer: any;
    rate: number;
    ratings: any;
    commentForm: FormGroup;

  constructor(
      private route: ActivatedRoute,
      private culturalOfferService: CulturalOfferService,
      private ratingService: RatingService,
      private fb: FormBuilder,
      private toastr: ToastrService
  ) {
      this.rate = 1;
      this.ratings = [];
      this.commentForm = this.fb.group({
          comment : [null, Validators.required]
      });
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

  submitNewComment(): void {
      const rating = {
          ratingValue: this.rate,
          comment: this.commentForm.value.comment
      };

      this.ratingService.submitRating(rating).subscribe(
          result => {
              this.toastr.success('Rating submitted!', 'New rating', {timeOut: 3000});
          },
          error => {
              console.log(error);
          }
      );


  }

}
