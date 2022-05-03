import { Sequelize } from "sequelize-typescript";
import { Person } from "../person/domain/Person";

const sequelize = new Sequelize({
  database: "personsdb",
  username: "root",
  password: "",
  dialect: "sqlite",
  storage: "./personsdb.sqlite",
  models: [Person],
});

export default sequelize;
