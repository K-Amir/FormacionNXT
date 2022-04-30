"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.makeCalc = exports.homeIndex = void 0;
const mathjs_1 = require("mathjs");
const homeIndex = (req, res) => {
    res.render("../src/views/home", {
        id: req.query.id,
    });
};
exports.homeIndex = homeIndex;
const makeCalc = (req, res) => {
    let { id, numero, accion, reset } = req.body;
    let cookieExpiration = new Date();
    cookieExpiration.setSeconds(cookieExpiration.getSeconds() + 60);
    if (req.cookies[`calculation${id}`]) {
        let newCookieValue = `${req.cookies[`calculation${id}`]}${accion}${numero}`;
        if (reset) {
            newCookieValue = "0";
            numero = 0;
        }
        res.cookie(`calculation${id}`, newCookieValue, {
            expires: cookieExpiration,
        });
        renderCalcResult(res, newCookieValue, id, numero);
        return;
    }
    let calculation = `${accion}${numero}`;
    res.cookie(`calculation${id}`, calculation, {
        expires: cookieExpiration,
    });
    renderCalcResult(res, calculation, id, numero);
};
exports.makeCalc = makeCalc;
const renderCalcResult = (res, calculation, id, numero) => {
    res.render("../src/views/result", {
        calculation,
        id,
        result: (0, mathjs_1.evaluate)(calculation),
        numero,
    });
};
