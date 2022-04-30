import { Router } from "express";
import { homeIndex, makeCalc } from "../controllers/homeController";

const homeRoutes = Router();
homeRoutes.get("/", homeIndex);
homeRoutes.post("/calc", makeCalc);

export { homeRoutes };
