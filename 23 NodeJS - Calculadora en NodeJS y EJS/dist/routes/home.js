"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.homeRoutes = void 0;
const express_1 = require("express");
const homeController_1 = require("../controllers/homeController");
const homeRoutes = (0, express_1.Router)();
exports.homeRoutes = homeRoutes;
homeRoutes.get("/", homeController_1.homeIndex);
homeRoutes.post("/calc", homeController_1.makeCalc);
