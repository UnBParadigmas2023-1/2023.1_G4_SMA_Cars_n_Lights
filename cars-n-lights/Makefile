PATH_PROJECT_JAR = target/cars-n-lights-1.0-SNAPSHOT.jar
PROJECT_GROUP    = org.fga.city
JADE_AGENTS      = mainAgent:$(PROJECT_GROUP).City;mainAgent2:$(PROJECT_GROUP).agents.CarAgent(A)
JADE_FLAGS 		 = -gui -agents "$(JADE_AGENTS)"

.PHONY:
	clean
	build-and-run

build-and-run:
	@echo "Gerando a build e executando o projeto"
	make clean build run

build:
	@echo "Gerando a build do projeto"
	./mvnw clean install

run:
	@echo "Executando o projeto com a última build criada"
	java -cp $(PATH_PROJECT_JAR) jade.Boot $(JADE_FLAGS)

clean:
	@echo "Removendo a build do projeto"
	./mvnw clean