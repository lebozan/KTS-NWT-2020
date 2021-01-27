import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CulturalOfferTypesComponent } from './cultural-offer-types.component';

describe('CulturalOfferTypesComponent', () => {
  let component: CulturalOfferTypesComponent;
  let fixture: ComponentFixture<CulturalOfferTypesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CulturalOfferTypesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CulturalOfferTypesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
