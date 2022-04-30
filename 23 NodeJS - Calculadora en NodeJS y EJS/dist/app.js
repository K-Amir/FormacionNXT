"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = __importDefault(require("express"));
const home_1 = require("./routes/home");
const cookie_parser_1 = __importDefault(require("cookie-parser"));
const app = (0, express_1.default)();
// Middlewares
app.use(express_1.default.static("assets"));
app.use(express_1.default.urlencoded());
app.use((0, cookie_parser_1.default)());
app.set("view engine", "ejs");
app.listen(3000, () => {
    console.log("Running");
});
// Routes
app.use(home_1.homeRoutes);
