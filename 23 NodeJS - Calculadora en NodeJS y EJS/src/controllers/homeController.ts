import cookieParser from "cookie-parser";
import { Request, Response } from "express";
import { evaluate, re } from "mathjs";

export const homeIndex = (req: Request, res: Response) => {
  res.render("../src/views/home", {
    id: req.query.id,
  });
};

export const makeCalc = (req: Request, res: Response) => {
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

const renderCalcResult = (
  res: Response,
  calculation: string,
  id: number,
  numero: number
) => {
  res.render("../src/views/result", {
    calculation,
    id,
    result: evaluate(calculation),
    numero,
  });
};
