#!/bin/sh

curl -X POST \
  http://localhost:7001/order/coffee/ \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
	"type": "ESPRESSO",
	"beanOrigin": "kenya"
}'