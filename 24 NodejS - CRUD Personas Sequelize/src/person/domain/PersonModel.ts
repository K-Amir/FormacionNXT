export interface PersonModel {
  id: number;
  user: string;
  name: string;
  surname: string;
  companyEmail: string;
  personalEmail: string;
  city: string;
  isActive: boolean;
  createdAt: Date;
  imageUrl: string;
  terminationDate: Date;
}
