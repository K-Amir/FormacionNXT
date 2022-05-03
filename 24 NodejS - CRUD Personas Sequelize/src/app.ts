import express from "express";
import { personRoutes } from "./person/application/personRoutes";
import sequelize from "./db/config";

const app = express();
app.use(express.json());

app.listen(3000, async () => {
  console.log("Running");
});
sequelize.sync({ force: true }).then(async () => {
  console.log("connected");
});

app.use(personRoutes);
