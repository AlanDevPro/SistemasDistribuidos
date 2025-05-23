// index.js
const express = require("express");
const { graphqlHTTP } = require("express-graphql");
const {
  GraphQLSchema,
  GraphQLObjectType,
  GraphQLString,
  GraphQLNonNull,
} = require("graphql");

// Datos simulados
const personas = [
  {
    CI: "12345678",
    Nombres: "Juan Carlos",
    PrimerApellido: "Perez",
    SegundoApellido: "Gomez",
  },
  {
    CI: "87654321",
    Nombres: "María Fernanda",
    PrimerApellido: "López",
    SegundoApellido: "Rodríguez",
  },
];

// Tipo GraphQL Persona
const PersonaType = new GraphQLObjectType({
  name: "Persona",
  fields: {
    CI: { type: new GraphQLNonNull(GraphQLString) },
    Nombres: { type: new GraphQLNonNull(GraphQLString) },
    PrimerApellido: { type: new GraphQLNonNull(GraphQLString) },
    SegundoApellido: { type: new GraphQLNonNull(GraphQLString) },
  },
});

// Root Query
const RootQueryType = new GraphQLObjectType({
  name: "Query",
  fields: {
    persona: {
      type: PersonaType,
      description: "Obtener una persona por CI",
      args: {
        CI: { type: new GraphQLNonNull(GraphQLString) },
      },
      resolve: (_, args) => {
        return personas.find((p) => p.CI === args.CI);
      },
    },
  },
});

// Esquema
const schema = new GraphQLSchema({
  query: RootQueryType,
});

// Servidor Express
const app = express();
app.use(
  "/graphql",
  graphqlHTTP({
    schema: schema,
    graphiql: true,
  })
);

app.listen(4000, () => {
  console.log("Servidor SEDUCA GraphQL corriendo en http://localhost:4000/graphql");
});
