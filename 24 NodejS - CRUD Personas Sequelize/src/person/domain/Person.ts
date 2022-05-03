import {
  AutoIncrement,
  Column,
  CreatedAt,
  Model,
  PrimaryKey,
  Table,
} from "sequelize-typescript";

@Table({
  tableName: "persons",
})
export class Person extends Model {
  @PrimaryKey
  @AutoIncrement
  @Column
  declare id: number;

  @Column
  declare user: string;

  @Column
  declare name: string;

  @Column
  declare surname: string;

  @Column
  declare companyEmail: string;

  @Column
  declare personalEmail: string;

  @Column
  declare city: string;

  @Column
  declare isActive: boolean;

  @CreatedAt
  declare createdAt: Date;

  @Column
  declare imageUrl: string;

  @Column
  declare terminationDate: Date;
}
