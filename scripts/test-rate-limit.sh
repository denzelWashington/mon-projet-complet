#!/bin/bash

URL="http://localhost:9000/api/users"

echo "🔥 Parallel test..."

for i in {1..100}
do
  curl -s -o /dev/null -w "Request $i -> %{http_code}\n" $URL &
done

wait