
-- Proyecto Sistema de Nomina (Base de Datos)

CREATE DATABASE bdNomina;


-- Tabla: "Empleado"

CREATE TABLE "empleado"
(
idempleado serial NOT NULL,
dni character(8) NOT NULL,
nombre varchar(60) NOT NULL,
fechanacimiento date,
gradoacademico varchar(20),
estadocivil varchar(15),
telefono varchar(80),
direccion varchar(120),
CONSTRAINT "EmpleadoPK" PRIMARY KEY (idempleado)
);


-- Tabla: "AFP"

CREATE TABLE "afp"
(
idafp serial NOT NULL,
nombre varchar(60) NOT NULL,
porcentaje double precision,
CONSTRAINT "AfpPK" PRIMARY KEY (idafp)
);


-- Tabla: "Contrato"

CREATE TABLE "contrato"
(
idcontrato serial NOT NULL,
idempleado integer NOT NULL,
idafp integer NOT NULL,
fechainiciocontrato date,
fechafincontrato date,
cargo varchar(50),  
asignacionfamiliar boolean,
totalhoras integer,
valorhora double precision,
condicion varchar(20) DEFAULT 'ACTIVO',
CONSTRAINT "ContratoPK" PRIMARY KEY (idcontrato),
	CONSTRAINT "ContratoAfpFK" FOREIGN KEY (idafp) REFERENCES "afp" (idafp), 
	CONSTRAINT "ContratoEmpleadoFK" FOREIGN KEY (idempleado) REFERENCES "empleado" (idempleado)
);


-- Tabla: "Periodo"


CREATE TABLE "periodo"
(
	idperiodo 	serial NOT NULL,
	estado varchar(10) DEFAULT 'PENDIENTE',
	fechainicioperiodo date NOT NULL,
	fechafinperiodo date NOT NULL,
	CONSTRAINT "PeriodoPK" PRIMARY KEY (idperiodo)
);


-- Tabla: "Concepto"


CREATE TABLE "concepto"
(
	idconcepto serial NOT NULL,
	idcontrato integer NOT NULL,
	idperiodo integer NOT NULL,
	horasextra integer,
	reintegros double precision,
	otrosingresos double precision,
	horasausente integer,
	adelantos double precision,
	otrosdescuentos double precision,
	CONSTRAINT "ConceptoPK" PRIMARY KEY (idconcepto),
	CONSTRAINT "ConceptoContratoFK" FOREIGN KEY (idcontrato) REFERENCES "contrato" (idcontrato), 
	CONSTRAINT "ConceptoPeriodoFK" FOREIGN KEY (idperiodo) REFERENCES "periodo" (idperiodo) 
);



-- Tabla: "Boleta"


CREATE TABLE "boleta"
(
	idboleta serial NOT NULL,
    	idcontrato integer NOT NULL,
    	idperiodo integer NOT NULL,
    	idconcepto integer NOT NULL,
    	fecha date,
    	totalhoras integer,
    	valorhora double precision,
    	asignacionfamiliar boolean,
	CONSTRAINT "BoletaPK" PRIMARY KEY (idboleta),
	CONSTRAINT "BoletaContratoFK" FOREIGN KEY (idcontrato)  REFERENCES "contrato" (idcontrato),
	CONSTRAINT "BoletaPeriodoFK" FOREIGN KEY (idperiodo)  REFERENCES "periodo" (idperiodo), 
	CONSTRAINT "BoletaConceptoFK" FOREIGN KEY(idconcepto) REFERENCES "concepto" (idconcepto)
);



