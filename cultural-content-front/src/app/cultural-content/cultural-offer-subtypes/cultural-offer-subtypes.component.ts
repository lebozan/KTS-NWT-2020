import { Component, OnInit } from '@angular/core';
import {CulturalOfferSubtypeService} from '../../services/cultural-offer-subtype.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {CulturalOfferTypeService} from '../../services/cultural-offer-type.service';

@Component({
    selector: 'app-cultural-offer-subtypes',
    templateUrl: './cultural-offer-subtypes.component.html',
    styleUrls: ['./cultural-offer-subtypes.component.scss']
})
export class CulturalOfferSubtypesComponent implements OnInit {
    culturalOfferSubtypes: Array<any>;
    culturalOfferTypes: Array<any>;
    formNew: FormGroup;
    formEdit: FormGroup;
    isEditing: number;

    constructor(
        private culturalOfferSubtypeService: CulturalOfferSubtypeService,
        private culturalOfferTypeService: CulturalOfferTypeService,
        private fb: FormBuilder
    ) {
        this.culturalOfferSubtypes = [];
        this.culturalOfferTypes = [];
        this.isEditing = 0;
        this.formNew = this.fb.group({
            name : [null, Validators.required],
            type : [null, Validators.required]
        });
        this.formEdit = this.fb.group({
            name : [null, Validators.required]
        });
    }

    ngOnInit(): void {
        this.culturalOfferSubtypeService.getAllSubtypes().subscribe(
            result => {
                this.culturalOfferSubtypes = result;
            }, error => {
                console.log(error.error);
            });

        this.culturalOfferTypeService.getAllTypes().subscribe(
            result => {
                this.culturalOfferTypes = result;
            }, error => {
                console.log(error.error);
            }
        );
    }

    deleteSubtype(id: number): void {
        this.culturalOfferSubtypeService.deleteSubtype(id).subscribe(result => {
            this.ngOnInit();
        }, error => {
            console.log(error);
        });
    }

    addNewSubtype(): void {
        const newSubtype: object = {
            name: this.formNew.value.name,
            type: {
                name: this.formNew.value.type
            }
        };


        this.culturalOfferSubtypeService.addNewSubtype(newSubtype).subscribe(
            result => {
                this.ngOnInit();
            }, error => {
                console.log(error.error);
            }
        );
    }

    editSubtypeName(subtypeId: number): void {
        this.isEditing = subtypeId;
    }

    submitSubtypeNameChange(subtypeId: number): void {
        this.culturalOfferSubtypeService.changeSubtypeName(subtypeId, this.formEdit.value.name).subscribe(
            result => {
                this.ngOnInit();
                this.isEditing = 0;
            }, error => {
                console.log(error.error);
            }
        );
    }
}
