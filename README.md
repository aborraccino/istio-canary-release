# Terminal 20 - Istio canary release
Shopper microservice orchestrated with K8s and Istio

## Why
Container orchestration platforms like Kubernetes only support traffic distribution based on instance scaling,
whereas Istio extends these capabilities and can split the traffic between two or more services based on:
- percentages
- user subsets

Another solution is Openshift which implemented Route resources.

## Set up a local environment

### Prerequisites
- Java 17
- Docker
- Minikube

### Create k8s cluster locally with Docker and minikube

Follow these steps will create a new namespace with istio injection enabled and a deployment with 2 replicas of the shopper service:
- build a Docker image with Jib and push to the local container register using the follwing command: `` ./gradlew jibDockerBuild ``
- load the image into Minikube with the command: `` minikube image load shopper:<tag> ``
- deploy the application in your local k8s, go to `/k8s-manifest` folder and run
    - `` kubectl apply -f deployment.yaml ``
- check if everything is running with the command `` kubectl get all -n t20 ``. You should be able to see the following Kubernetes resources:
```shell
NAME                          READY   STATUS    RESTARTS   AGE
pod/shopper-dd77664c8-7jdtc   2/2     Running   0          32s
pod/shopper-dd77664c8-fsfpp   2/2     Running   0          32s

NAME              TYPE        CLUSTER-IP     EXTERNAL-IP   PORT(S)    AGE
service/shopper   ClusterIP   10.100.51.53   <none>        8080/TCP   33s

NAME                      READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/shopper   2/2     2            2           33s

NAME                                DESIRED   CURRENT   READY   AGE
replicaset.apps/shopper-dd77664c8   2         2         2       33s
```

- expose the application outside with this command: ``` kubectl port-forward service/shopper 8080:8080 -n t20 ```

- run this command to test if the service is reachable from the host machine: ``curl -m 2 http://localhost:8080/shops/WEDD321``. 
- You should get this response:
```json
{
  "id": "WEDD321",
  "email": "sport@shop.it",
  "tel": "07851123456",
  "address": "Science Park, 270 Milton Rd, Milton, Cambridge CB4 0WE"
}
```

## Simulate the canary release

### k8s native
- ``kubectl run tmp --image=busybox --restart=Never -i -t --rm -n t20 -- wget -O- -T 3 http://shopper:8080/shops/RST1656``

### istio
- ``kubectl port-forward service/istio-ingressgateway 8080:80 -n istio-system ``
