#!/bin/bash

# Define service directories
SERVICES=("apigateway" "authenticator" "userinfo" "messenger")
PIDS=() # To store process IDs of the running services

# Function to build and run a service
build_and_run_service() {
    local service_dir=$1
    echo "Building $service_dir..."
    mvn -f $service_dir/pom.xml package -DskipTests

    if [ $? -ne 0 ]; then
        echo "Build failed for $service_dir. Exiting."
        stop_services
        exit 1
    fi

    echo "Running $service_dir..."
    java -jar $service_dir/target/*.jar &
    PIDS+=($!) # Save the process ID of the running service
}

# Function to stop all services
stop_services() {
    echo "Stopping all services..."
    for pid in "${PIDS[@]}"; do
        echo "Stopping process ID $pid"
        kill $pid 2>/dev/null

        # Confirm the process is terminated
        wait $pid 2>/dev/null
        echo "Process ID $pid stopped."
    done
    echo "All services stopped."
}

# Build and run all services
for service in "${SERVICES[@]}"; do
    build_and_run_service $service
done

# Trap SIGINT (Ctrl+C) or termination signals to stop services
trap stop_services EXIT

# Wait to keep the script running
echo "All services are running."
wait
