import express from "express";
import sequelize from "./db/config";
import { personRoutes } from "./routes/personRoutes";

const app = express();
app.use(express.json());

app.listen(3000, async () => {
  console.log("Running");
});

sequelize.sync();

app.use(personRoutes);
