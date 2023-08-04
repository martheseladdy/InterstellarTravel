#!/bin/bash

# Endpoint URL for DynamoDB Local
ENDPOINT_URL="http://localhost:8000"

# Get a list of all table names
table_names=$(aws dynamodb list-tables --endpoint-url "$ENDPOINT_URL" --output json | jq -r '.TableNames[]')

# Check if there are any tables
if [[ -z "$table_names" ]]; then
    echo "No tables found."
else
    echo "List of tables:"
    for table in $table_names
    do
        echo "- $table"
    done
fi