import { TestBed } from '@angular/core/testing';

import { CulturalOfferSubtypeService } from './cultural-offer-subtype.service';

describe('CulturalOfferSubtypeService', () => {
  let service: CulturalOfferSubtypeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CulturalOfferSubtypeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
