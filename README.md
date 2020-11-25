# Spring Boot API With Istio Service Mesh And Open Tracing (Jaeger) Example
Sample project to demonstrate open tracing on kubernetes using Istio Sidecar and Jaeger. It mimics the communication between 2 Spring Boot API's and Jaeger will be collecting traces upon subsequent requests.

Prerequisites:
- [Docker](https://www.docker.com/products/docker-desktop)
- [Kubernetes](https://minikube.sigs.k8s.io/docs/start/) (Can use in-built Docker Desktop version of Kubernetes or Follow the link to install minikube)
- [kubectl](https://kubernetes.io/docs/tasks/tools/install-kubectl/)
- [Istioctl](https://istio.io/latest/docs/ops/diagnostic-tools/istioctl/) (Mac users can install it using [homebrew](https://formulae.brew.sh/formula/istioctl))
- Istio 
    - Run the below commands once _istioctl_ is installed in your kubernetes cluster:
        ```
       istioctl manifest apply --set profile=demo
       ```
    - After the above command is successful, run the below command:
        ```
      kubectl label namespace default istio-injection=enabled
      ```
    - Alternatively, you can use the below command **ignoring the above 2 commands**:
      ```
      kubectl apply -f install/kubernetes/istio.yaml
      ```
- Jaeger-all-in-one
    - To install Jaeger-all-in-one on Istio, run the below command from inside the project folder:
        ```
      kubectl create -n istio-system -f kubectl create -n istio-system -f jaeger-all-in-one/jaeger-all-in-one-template.yml
      ```
    
### Building Docker Images:
The project is a multi-module project, inorder for you to test the opentracing, we need to build 2 docker images each by navigation into the respective module folder.
Issue the following command:
```
mvn clean package
```
Or
```
docker build -t <project-name>:<version> .
```
Note: Replace project-name and version with _artifactId_ and _version_ from the respective pom.xml files.

### Creating Kubernetes Resources
From the project root folder, issue the below command:
```
kubectl apply -f k8s
```
Note: All configuration files for deploying resources in Kubernates are located in **k8s** folder.

Check if resources are up and running. Use the below commands
```
# Deployments
kubectl get deployments

# Services
kubectl get services

# Pods
kubectl get pods

# Gateways
kubectl get gateways

# Istio Gateways
kubectl get gateway.networking.istio.io

# Virtual Services
kubectl get virtualservice.networking.istio.io

# Destination Rules (In case of service mesh implementations)
kubectl get destinationrule.networking.istio.io

# Detailed information about gateways
kubectl describe svc istio-ingressgateway -n istio-system
```
### Testing Opentracing
If running on Docker Deskop built-in Kubernetes, issue the below command:
```
curl http://localhost/demo1
```
If running on minikube, then follow the article [here](https://istio.io/latest/docs/setup/getting-started/#determining-the-ingress-ip-and-ports) to create GATEWAY_URL environment variable. Then issue the below command:
```
curl http://GATEWAY_URL/demo1
```
Trigger 2 or 3 requests using the above commands and issue the below command to open Jaeger Dashboard and analyze traces:
```
istioctl dashboard jaeger
```
That should open up a Jaeger dashboard in a browser window. You can search and analyze traces from the above request.

Happy Learning!!!
### References

- https://blog.malathi.dev/2020/08/14/spring-boot-jaeger-istio.html
- https://piotrminkowski.com/2020/06/01/service-mesh-on-kubernetes-with-istio-and-spring-boot/
- https://gist.github.com/stevenc81/2c6840784c6223cdbd62cdd1563a4811

