# Sample application for DevOps process demonstration.

## CLEAN project

````
mvn clean
````

## BUILD project

````
mvn package
````

## TEST application

````
mvn test
````

## BUILD and TEST project

````
mvn install
````

## RUN application

````
mvn liberty:run-server
````

## build Docker IMAGE. Don't forget to change `<VERSION_TAG>`

````
docker build -t mycluster.icp:8500/demo/devops-demo-backend:<VERSION_TAG>.
````

## DEPLOY to Kubernetes

````
kubectl create -f Kubernetes.yaml
````
