import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CulturalOfferSubtypesComponent } from './cultural-offer-subtypes.component';

describe('CulturalOfferSubtypesComponent', () => {
  let component: CulturalOfferSubtypesComponent;
  let fixture: ComponentFixture<CulturalOfferSubtypesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CulturalOfferSubtypesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CulturalOfferSubtypesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
