# Hosted Test Orchestration Python Examples

This code shows you how to execute tests three different ways:hosted by Sauce Labs, remotely on Sauce Labs and locally.
* Hosted by Sauce Labs
* Remotely on Sauce Labs
* Locally

## Prerequisites

### Sauce Labs
1. Get your [username and access key](https://app.saucelabs.com/user-settings)
2. [Set an environment variable](https://docs.saucelabs.com/basics/environment-variables/#setting-up-environment-variables) for Sauce Labs Username as `SAUCE_USERNAME`
3. [Set an environment variable](https://docs.saucelabs.com/basics/environment-variables/#setting-up-environment-variables) for Sauce Labs Access Key as `SAUCE_ACESS_KEY`

### Docker Hub
1. [Create an account](https://hub.docker.com/signup)
2. [Set an environment variable](https://docs.saucelabs.com/basics/environment-variables/#setting-up-environment-variables) for Docker Hub Username as `DOCKER_USERNAME`
3. [Set an environment variable](https://docs.saucelabs.com/basics/environment-variables/#setting-up-environment-variables) for Docker Hub Password as `DOCKER_PASSWORD`

### Git
1. Install [Git](https://git-scm.com/doc)

### Download this code
1. [Fork this Repo](https://github.com/saucelabs-training/hto/fork) on GitHub
2. Go to your new fork, click the big green "Code" button and copy the https line
3. Make a new directory for your projects if you don't have one already
   ```shell
   mkdir ~/projects
   ```
4. Clone from your fork onto your machine
   ```shell
   cd ~/projects
   git clone <LINK_COPIED_IN_STEP_2>
   ```

### Install and Run Docker Desktop
1. [Download](https://docs.docker.com/get-docker/)
2. Open the application
3. In a terminal run this to make sure it is running:
   ```shell
   docker info
   ```
If Docker is not running you get an error like:
   ```text
   ERROR: Cannot connect to the Docker daemon at unix:///var/run/docker.sock. Is the docker daemon running?
   ```

## Kick off these tests from Local Machine

### Locally
This skips Sauce Labs all together and runs your tests on your local machine without parallel:

```shell
pytest --selenium local
```

### Remotely on Sauce Labs
This is the traditional way of running tests on Sauce Labs.

This repo uses pytest and pytest-xdist for running tests in parallel. Specify the amount of parallel execution with `-n`.
This will run all 15 tests in parallel:

```shell
pytest -n 15
```

### Hosted by Sauce Labs
This is the exciting new way to run your tests on Sauce Labs.
The overall execution time should be less (the longer the tests the more performance improvements), but there
is going to be some overhead for the container to get loaded and the tests to start.

#### Build and Deploy Container
1. Login to Docker Hub
   ```shell
   docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD
   ```
2. Temporarily define Docker image with tag (important: make sure you do not duplicate an existing tag):
    ```shell
    export DOCKER_IMAGE=$DOCKER_USERNAME/hto-demo-python:0.0.1
    ```
3. Build with tag
    ```shell
    docker build -t $DOCKER_IMAGE ./
    ```
4. Push to Docker Hub
    ```shell
    docker push $DOCKER_IMAGE
    ```

#### Run Saucectl
1. [Download saucectl](https://docs.saucelabs.com/dev/cli/saucectl/#installing-saucectl)
2. Execute (saucectl looks at details in `~/.sauce/config.yml` to know what to do):
    ```shell
    saucectl run
    ```

## Contributing

Bug reports and pull requests are welcome on GitHub at https://github.com/saucelabs-training/hto.

## License

The code is available as open source under the terms of the [MIT License](https://opensource.org/licenses/MIT).
