CREATE TABLE nota_caracteristica_requisito
(
    id                  SERIAL PRIMARY KEY,
    requisito_id        integer NOT NULL,
    caracteristica_id   integer NOT NULL,
    nota_caracteristica double PRECISION,
    FOREIGN KEY (requisito_id) REFERENCES requisito (id),
    FOREIGN KEY (caracteristica_id) REFERENCES caracteristica (id),
    UNIQUE (requisito_id, caracteristica_id)
);

CREATE TABLE tipo_error_caracteristica
(
    id                SERIAL PRIMARY KEY,
    requisito_id      integer NOT NULL,
    caracteristica_id integer NOT NULL,
    tipo_error_id     integer NOT NULL,
    dde               boolean,
    dii               boolean,
    var               boolean,
    FOREIGN KEY (requisito_id) REFERENCES requisito (id),
    FOREIGN KEY (caracteristica_id) REFERENCES caracteristica (id),
    FOREIGN KEY (tipo_error_id) REFERENCES tipo_error (id),
    UNIQUE (requisito_id, caracteristica_id, tipo_error_id)
);

-->---------------------------------------------------------------------------------------------------------------------

INSERT INTO public.caracteristica(id, descripcion, descripcion_opuesta, nombre, nombre_opuesto)
VALUES (1, 'Se encuentra en el resultado o producto', 'No se encuentra en el resultado o producto', 'Correcto', 'Incorrecto');
INSERT INTO public.caracteristica(id, descripcion, descripcion_opuesta, nombre, nombre_opuesto)
VALUES (2, 'Tiene sólo una interpretación, para su creación y para su uso', 'No tiene sólo interpretación, para su creación y para su uso', 'Inequívoco', 'Ambiguo');
INSERT INTO public.caracteristica(id, descripcion, descripcion_opuesta, nombre, nombre_opuesto)
VALUES (3, 'Está relacionado con la funcionalidad de general que se espera', 'No está relacionado con la funcionalidad de general que se espera', 'Completo', 'Incompleto');
INSERT INTO public.caracteristica(id, descripcion, descripcion_opuesta, nombre, nombre_opuesto)
VALUES (4, 'Genera de forma individual conflicto con los otros requisitos', 'No genera de formula individual conflicto con los otros requisitos', 'Consistente', 'Débil');
INSERT INTO public.caracteristica(id, descripcion, descripcion_opuesta, nombre, nombre_opuesto)
VALUES (5, 'Tiene un identificador de importancia que lo relaciona al resultado o producto, esencial o deseable', 'No tiene un identificador de importancia que lo relaciona al resultado o producto, esencial o deseable', 'Importante', 'Intrascendente');
INSERT INTO public.caracteristica(id, descripcion, descripcion_opuesta, nombre, nombre_opuesto)
VALUES (6, 'Tiene un número mínimo de cambios realizados o que se espera realizar', 'No tiene un número mínimo de cambios realizados o que se espera realizar', 'Estable', 'Inestable');
INSERT INTO public.caracteristica(id, descripcion, descripcion_opuesta, nombre, nombre_opuesto)
VALUES (7, 'Tiene asociado algún proceso con el que se pueda verificar su relación directa con el resultado o producto', 'No tiene asociado algún proceso con el que se pueda verificar su relación directa con el resultado o producto', 'Comprobable', 'No Comprobable');
INSERT INTO public.caracteristica(id, descripcion, descripcion_opuesta, nombre, nombre_opuesto)
VALUES (8, 'Tiene un origen claro y facilita sus referencias en el desarrollo futuro o documentación del mismo', 'No tiene origen claro y no facilita sus referencias en el desarrollo futuro o documentación del mismo', 'Identificable', 'No Identificable');
INSERT INTO public.caracteristica(id, descripcion, descripcion_opuesta, nombre, nombre_opuesto)
VALUES (9, 'Es identificable dirigido hacia atrás, como identificable dirigido hacia adelante', 'No es identificable dirigido hacia atrás, como identificable dirigido hacia adelante', 'Trazable', 'No Trazable');

INSERT INTO public.tipo_error(id, descripcion, nombre)
VALUES (1, 'Especificación incompleta o errónea', 'EIE');
INSERT INTO public.tipo_error(id, descripcion, nombre)
VALUES (2, 'Interpretación no adecuada de la comunicación con el cliente o usuario', 'MCC');

-->---------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION insertar_datos_iniciales()
    RETURNS TRIGGER AS $$
BEGIN
INSERT INTO public.nota_caracteristica_requisito (requisito_id, caracteristica_id, nota_caracteristica)
VALUES (NEW.id, 1, 0),
       (NEW.id, 2, 0),
       (NEW.id, 3, 0),
       (NEW.id, 4, 0),
       (NEW.id, 5, 0),
       (NEW.id, 6, 0),
       (NEW.id, 7, 0),
       (NEW.id, 8, 0),
       (NEW.id, 9, 0);

INSERT INTO public.tipo_error_caracteristica (requisito_id, caracteristica_id, tipo_error_id, dde, dii, var)
VALUES (NEW.id, 1, 1, false, false, false),
       (NEW.id, 2, 1, false, false, false),
       (NEW.id, 3, 1, false, false, false),
       (NEW.id, 4, 1, false, false, false),
       (NEW.id, 5, 2, false, false, false),
       (NEW.id, 6, 2, false, false, false),
       (NEW.id, 7, 2, false, false, false),
       (NEW.id, 8, 1, false, false, false),
       (NEW.id, 9, 1, false, false, false);

INSERT INTO public.operacion (caracteristicas_evaluadas, nivel_adecuacion, nivel_caracteristica_puntuacion, promedio_calculado, puntaje_maximo, requisito_id)
VALUES (0, 0, 0, 0, 0, NEW.id);

RETURN NEW;
END
$$ LANGUAGE plpgsql;

CREATE TRIGGER trgRequisito_Insert
    AFTER INSERT
    ON requisito
    FOR EACH ROW
EXECUTE FUNCTION insertar_datos_iniciales();

-->---------------------------------------------------------------------------------------------------------------------