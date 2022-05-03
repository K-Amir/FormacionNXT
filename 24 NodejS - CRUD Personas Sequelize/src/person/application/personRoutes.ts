import { Router } from "express";
import {
  findPersonById,
  createPerson,
  deletePersonById,
  findAllPersons,
  updatePersonById,
} from "./personController";

const personRoutes = Router();

personRoutes.get("/api/v0/persons/:id", findPersonById);
personRoutes.post("/api/v0/persons", createPerson);
personRoutes.delete("/api/v0/persons/:id", deletePersonById);
personRoutes.get("/api/v0/persons", findAllPersons);
personRoutes.put("/api/v0/persons/:id", updatePersonById);

export { personRoutes };
