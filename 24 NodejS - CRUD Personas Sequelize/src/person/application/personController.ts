import { Request, Response } from "express";
import { Person } from "../domain/Person";
import { PersonModel } from "../domain/PersonModel";

export const findPersonById = async (req: Request, res: Response) => {
  let person: PersonModel | null = await Person.findByPk(req.params.id);
  if (person) {
    res.send(person);
  } else {
    res.send({
      error: "Could not find person with the specified id => " + req.params.id,
    });
  }
};

export const createPerson = async (req: Request, res: Response) => {
  let person: PersonModel = req.body;
  await Person.create({ ...person });
  res.send({ success: "Person created successfully" });
};

export const deletePersonById = async (req: Request, res: Response) => {
  const { id } = req.params;
  Person.destroy({ where: { id } });
  res.send({ success: "Person deleted successfully" });
};

export const findAllPersons = async (req: Request, res: Response) => {
  let personsList: PersonModel[] = await Person.findAll();
  res.send(personsList);
};

export const updatePersonById = async (req: Request, res: Response) => {
  const { id } = req.params;
  let person: Person | null = await Person.findByPk(id);
  if (person) {
    let personToUpdate: PersonModel = req.body;
    person.update({ ...personToUpdate });
    person.save();
    res.send({ success: "Person updated successfully" });
  }
  if (!person) {
    res.send({ error: "Failed to update" });
  }
};
