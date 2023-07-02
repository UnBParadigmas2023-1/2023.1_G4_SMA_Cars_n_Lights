PATH_PROJECT_JAR = target/cars-n-lights-1.0-SNAPSHOT.jar
PROJECT_GROUP    = org.fga.paradigmas
JADE_AGENTS      = mainAgent:$(PROJECT_GROUP).App;commander1:$(PROJECT_GROUP).models.TrafficLightsCommander;trafficLight1:$(PROJECT_GROUP).models.TrafficLights;trafficLight2:$(PROJECT_GROUP).models.TrafficLights;
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

build-and-run-win:
	@echo "Gerando a build e executando o projeto"
	make clean-win build-win run

build-win:
	@echo "Gerando a build do projeto"
	.\mvnw clean install

clean-win:
	@echo "Removendo a build do projeto"
	.\mvnw clean