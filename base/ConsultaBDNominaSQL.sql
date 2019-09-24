
-- Proyecto Sistema de Nomina (Base de Datos)

/*CREATE DATABASE bdNomina
go
*/

-- Tabla: "Empleado"


CREATE TABLE "Empleado"
(
    idempleado integer NOT NULL,
    dni character(8) NOT NULL,
    nombre character varying(60) NOT NULL,
    fechanacimiento date,
    gradoacademico character varying(20),
    estadocivil character varying(8),
    telefono character varying(80),
    direccion character varying(120),
    CONSTRAINT "empleadoPK" PRIMARY KEY (idempleado)
);


-- Tabla: "AFP"


CREATE TABLE "Afp"
(
    idafp integer NOT NULL,
    nombre character varying(60) NOT NULL,
    porcentaje double precision,
    CONSTRAINT "AfpPK" PRIMARY KEY (idafp)
);


-- Tabla: "Contrato"


CREATE TABLE "Contrato"
(
    idcontrato integer NOT NULL,
    idempleado integer NOT NULL,
    idafp integer NOT NULL,
    fechainiciocontrato date,
    fechafincontrato date,
    cargo character varying(50),  
    asignacionfamiliar bit,
    totalhoras integer,
    valorhora double precision,
    CONSTRAINT "ContratoPK" PRIMARY KEY (idcontrato),
    CONSTRAINT "ContratoAfpFK" FOREIGN KEY (idafp) REFERENCES "Afp" (idafp), 
    CONSTRAINT "ContratoEmpleadoFK" FOREIGN KEY (idempleado) REFERENCES "Empleado" (idempleado)
);


-- Tabla: "Periodo"


CREATE TABLE "Periodo"
(
    idperiodo integer NOT NULL,
    descripcion character varying(30),
    estado character varying(10),
    fechainicioperiodo date NOT NULL,
    fechafinperiodo date NOT NULL,
    CONSTRAINT "Periodo_pkey" PRIMARY KEY (idperiodo)
);


-- Tabla: "Concepto_Ingreso_Descuento"


CREATE TABLE "Concepto_Ingreso_Descuento"
(
    idconceptoingresodescuento integer NOT NULL,
    idcontrato integer NOT NULL,
    idperiodo integer NOT NULL,
    horasextra integer,
    reintegros double precision,
    otrosingresos double precision,
    horasausente integer,
    adelantos double precision,
    otrosdescuentos double precision,
    CONSTRAINT "ConceptoIngresoDescuentoPK" PRIMARY KEY (idconceptoingresodescuento),
    CONSTRAINT "ConceptoContratoFK" FOREIGN KEY (idcontrato) REFERENCES "Contrato" (idcontrato), 
    CONSTRAINT "ConceptoPeriodoFK" FOREIGN KEY (idperiodo) REFERENCES "Periodo" (idperiodo) 
);



-- Tabla: "Boleta"


CREATE TABLE "Boleta"
(
    idboleta integer NOT NULL,
    idcontrato integer NOT NULL,
    idperiodo integer NOT NULL,
	idconceptoingresodescuento integer NOT NULL,
    fecha date,
    totalhoras integer,
    valorhora double precision,
    asignacionfamiliar bit,
    CONSTRAINT "BoletaPK" PRIMARY KEY (idboleta),
    CONSTRAINT "BoletaContratoFK" FOREIGN KEY (idcontrato) REFERENCES "Contrato" (idcontrato),
    CONSTRAINT "BoletaPeriodoFK" FOREIGN KEY (idperiodo) REFERENCES "Periodo" (idperiodo), 
	CONSTRAINT "BoletaConceptosFK" FOREIGN KEY (idconceptoingresodescuento) REFERENCES "Concepto_Ingreso_Descuento" (idconceptoingresodescuento)
);



