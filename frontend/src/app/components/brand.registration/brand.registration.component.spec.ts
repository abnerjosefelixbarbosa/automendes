import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BrandRegistrationComponent } from './brand.registration.component';

describe('BrandRegistrationComponent', () => {
  let component: BrandRegistrationComponent;
  let fixture: ComponentFixture<BrandRegistrationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BrandRegistrationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BrandRegistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
