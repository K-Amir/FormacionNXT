import express from "express";
import { homeRoutes } from "./routes/home";
import cookieParser from "cookie-parser";

const app = express();

// Middlewares
app.use(express.static("assets"));
app.use(express.urlencoded());
app.use(cookieParser());

app.set("view engine", "ejs");

app.listen(3000, () => {
  console.log("Running");
});

// Routes
app.use(homeRoutes);
