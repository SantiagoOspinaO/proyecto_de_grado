INSERT INTO public.caracteristicas(
	id, descripcion, nombre)
	VALUES (1, 'Se encuentra en el resultado', 'Correcto');

INSERT INTO public.caracteristicas(
	id, descripcion, nombre)
	VALUES (2, 'Tiene sólo una interpretación, para su creación y para su uso', 'Inequívoco');

INSERT INTO public.caracteristicas(
	id, descripcion, nombre)
	VALUES (3, 'Está relacionado con la funcionalidad de general que se espera', 'Completo');

INSERT INTO public.caracteristicas(
	id, descripcion, nombre)
	VALUES (4, 'No genera de forma individual conflicto con los otros requisitos', 'Consistente');

INSERT INTO public.caracteristicas(
	id, descripcion, nombre)
	VALUES (5, 'Tiene un identificador de importancia que lo relaciona al resultado o producto', 'Importante');

INSERT INTO public.caracteristicas(
	id, descripcion, nombre)
	VALUES (6, 'Tiene un número mínimo de camnios realizados o que se espera realizar', 'Estable');

INSERT INTO public.caracteristicas(
	id, descripcion, nombre)
	VALUES (7, 'Tiene asociado algun proceso con el que se pueda verificar su relacion directa con el resultado o producto', 'Comprobable');

INSERT INTO public.caracteristicas(
	id, descripcion, nombre)
	VALUES (8, 'Tiene un origen claro y facilita sus refrencias en el desarrollo futuro o documentacion del mismo', 'Identificable');

INSERT INTO public.caracteristicas(
	id, descripcion, nombre)
	VALUES (9, 'Es identificado dirigido hacia atras, como identificable dirigido hacia adelante', 'Trazable');

INSERT INTO public.caracteristicas(
	id, descripcion, nombre)
	VALUES (10, 'No se encuentra en el resultado o producto', 'Incorrecto');

INSERT INTO public.caracteristicas(
	id, descripcion, nombre)
	VALUES (11, 'No tiene sólo interpretación, para su creación y para su uso', 'Ambiguo');

INSERT INTO public.caracteristicas(
	id, descripcion, nombre)
	VALUES (12, 'No esta reacionado con la funcionalidad en general que se espera', 'Incompleto');

INSERT INTO public.caracteristicas(
	id, descripcion, nombre)
	VALUES (13, 'No genera formula individual conflicto con los otros requisitos', 'Débil');

INSERT INTO public.caracteristicas(
	id, descripcion, nombre)
	VALUES (14, 'No tiene un identificador de importancia que lo relaciona al resultado o producto, escencial o deseable', 'Intrascendente');

INSERT INTO public.caracteristicas(
	id, descripcion, nombre)
	VALUES (15, 'No tiene un número mínimo de cambios relaizados o que se espera realizar', 'Inestable');

INSERT INTO public.caracteristicas(
	id, descripcion, nombre)
	VALUES (16, 'No tiene asociado, algún proceso con el que se pueda verificar su relación directa con el resultado o producto', 'No comprobable');

INSERT INTO public.caracteristicas(
	id, descripcion, nombre)
	VALUES (17, 'No tiene origen claro y facilita sus referencias en el desarrollo futuro o documentción del mismo', 'No Identificable');

INSERT INTO public.caracteristicas(
	id, descripcion, nombre)
	VALUES (18, 'No es identificable dirigido hacia atrás, como identificable dirigido hacia adelante', 'No Trazable');


INSERT INTO public.errores(
	id, descripcion, nombre)
	VALUES (1, 'Especificación Incompleta o erróne', EIE);

INSERT INTO public.errores(
	id, descripcion, nombre)
	VALUES (2, 'Interpretación no adecuada de la comunicación con el cliente o usuario', 'MCC');


INSERT INTO public.cause_errors(
	id, descripcion, nombre)
	VALUES (1,'Desviación deliverada de la especificación', 'DDE');

INSERT INTO public.cause_errors(
	id, descripcion, nombre)
	VALUES (2,'Documentación imprecisa o incomplta de las especificaciones', 'DII');

INSERT INTO public.cause_errors(
	id, descripcion, nombre)
	VALUES (3,'Otros aspectos que se puedan considerar', 'VAR');