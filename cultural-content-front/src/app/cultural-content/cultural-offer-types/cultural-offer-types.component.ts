import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {CulturalOfferTypeService} from '../../services/cultural-offer-type.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
    selector: 'app-cultural-offer-types',
    templateUrl: './cultural-offer-types.component.html',
    styleUrls: ['./cultural-offer-types.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class CulturalOfferTypesComponent implements OnInit {
    culturalOfferTypes: Array<any>;
    formNew: FormGroup;
    formEdit: FormGroup;
    isEditing: number;

    constructor(
        private culturalOfferTypeService: CulturalOfferTypeService,
        private fb: FormBuilder
    ) {
        this.culturalOfferTypes = [];
        this.isEditing = 0;
        this.formNew = this.fb.group({
            name : [null, Validators.required]
        });
        this.formEdit = this.fb.group({
            name : [null, Validators.required]
        });
    }

    ngOnInit(): void {
        this.culturalOfferTypeService.getAllTypes().subscribe(
        result => {
            this.culturalOfferTypes = result;
        }, error => {
            console.log(error.error);
        });
    }

    deleteType(id: number): void {
        this.culturalOfferTypeService.deleteType(id).subscribe(result => {
            this.ngOnInit();
        }, error => {
            console.log(error);
        });
    }

    addNewType(): void {
        const newType = {
            name: this.formNew.value.name
        };
        this.culturalOfferTypeService.addNewType(newType).subscribe(
            result => {
                this.ngOnInit();
            }, error => {
                console.log(error.error);
            }
        );
    }

    editTypeName(typeId: number): void {
        this.isEditing = typeId;
    }

    submitTypeNameChange(typeId: number): void {
        this.culturalOfferTypeService.changeTypeName(typeId, this.formEdit.value.name).subscribe(
            result => {
                this.ngOnInit();
                this.isEditing = 0;
            }, error => {
                console.log(error.error);
            }
        );
    }


}
