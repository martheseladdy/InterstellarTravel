#!/bin/bash

function put_accelerator() {
    connections=$(echo "$3" | jq -c '.[] | {"M": {"id":{"S": .id}, "hu":{"N": .hu|tostring}} }' | tr '\n', ',' | rev | cut -c2- | rev )
    json="{\"id\": {\"S\": \"$1\"}, \"name\": {\"S\": \"$2\"}, \"connections\": {\"L\": [$connections]}}"
    echo "${json}"
    aws dynamodb put-item \
        --table-name accelerator  \
        --item "$json" \
        --endpoint-url http://localhost:8000
}

aws dynamodb delete-table \
    --table-name accelerator \
    --endpoint-url http://localhost:8000

aws dynamodb create-table \
    --table-name accelerator \
    --attribute-definitions \
        AttributeName=id,AttributeType=S \
    --key-schema \
        AttributeName=id,KeyType=HASH \
    --provisioned-throughput \
        ReadCapacityUnits=10,WriteCapacityUnits=5 \
    --endpoint-url http://localhost:8000

put_accelerator "SOL" "Sol" '[{ "id": "RAN", "hu": "100" }, { "id": "PRX", "hu": "90" }, { "id": "SIR", "hu": "100" }, { "id": "ARC", "hu": "200" }, { "id": "ALD", "hu": "250" }]'
put_accelerator "PRX" "Proxima" '[{ "id": "SOL", "hu": "90" }, { "id": "SIR", "hu": "100" }, { "id": "ALT", "hu": "150" }]'
put_accelerator "SIR" "Sirius" '[{ "id": "SOL", "hu": "80" }, { "id": "PRX", "hu": "10" }, { "id": "CAS", "hu": "200" }]'
put_accelerator "CAS" "Castor" '[{ "id": "SIR", "hu": "200" }, { "id": "PRO", "hu": "120" }]'
put_accelerator "PRO" "Procyon" '[{ "id": "CAS", "hu": "80" }]'
put_accelerator "DEN" "Denebula" '[{ "id": "PRO", "hu": "5" }, { "id": "ARC", "hu": "2" }, { "id": "FOM", "hu": "8" }, { "id": "RAN", "hu": "100" }, { "id": "ALD", "hu": "3" }]'
put_accelerator "RAN" "Ran" '[{ "id": "SOL", "hu": "100" }]'
put_accelerator "ARC" "Arcturus" '[{ "id": "SOL", "hu": "500" }, { "id": "DEN", "hu": "120" }]'
put_accelerator "FOM" "Fomalhaut" '[{ "id": "PRX", "hu": "10" }, { "id": "DEN", "hu": "20" }, { "id": "ALS", "hu": "9" }]'
put_accelerator "ALT" "Altair" '[{ "id": "FOM", "hu": "140" }, { "id": "VEG", "hu": "220" }]'
put_accelerator "VEG" "Vega" '[{ "id": "ARC", "hu": "220" }, { "id": "ALD", "hu": "580" }]'
put_accelerator "ALD" "Aldermain" '[{ "id": "SOL", "hu": "200" }, { "id": "ALS", "hu": "160" }, { "id": "VEG", "hu": "320" }]'
put_accelerator "ALS" "Alshain" '[{ "id": "ALT", "hu": "1" }, { "id": "ALD", "hu": "1" }]'