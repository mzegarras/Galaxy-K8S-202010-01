apiVersion: v1
kind: Service
metadata:
  name: mysql
spec:
  ports:
  - port: 3306
  selector:
    app: mysql
  type: ClusterIP
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: mysql
spec:
  selector:
    matchLabels:
      app: mysql
  strategy:
    type: Recreate
  template:
    metadata:
      labels:
        app: mysql
    spec:
      containers:
      - image: mysql:5.7
        name: mysql
        env:
          # Use secret in real usage
        - name: MYSQL_ROOT_PASSWORD
          value: password
        - name: MYSQL_DATABASE
          value: appdb
        - name: MYSQL_DATABASE
          value: appdb
        - name: MYSQL_USER
          value: appdb
        - name: MYSQL_PASSWORD
          value: demopwd                    
        ports:
        - containerPort: 3306
          name: mysql
