import { TestBed } from '@angular/core/testing';

import { BikeCompanyService } from './bike-company.service';

describe('BikeCompanyService', () => {
  let service: BikeCompanyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BikeCompanyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
