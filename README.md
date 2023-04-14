# Hosted Test Orchestration Examples

Sauce Labs has an exciting new feature for running your tests with [Hosted Orchestration](https://docs.saucelabs.com/hosted-orchestration/)

This mono-repo has example code in Java & Python (more language examples soon) for how to run a small set of tests
locally, remotely on Sauce Labs, and hosted by Sauce Labs for comparison.

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

## Executing from Local Machine

You can only run one language at a time, so make sure you change directories into the language subdirectory,
and check out the README:

* [Java](./java/README.md)
* [Python](./python/README.md)

## Executing on Github Actions

This repo also defines workflows for kicking off tests from Github Actions.

1. make sure you have properly forked this code to your own organization (instructions above)
2. Add these secrets to github: (https://github.com/<YOUR_GITHUB_ORG>/hto/settings/secrets/actions)
   * SAUCE_USERNAME
   * SAUCE_ACCESS_KEY
   * DOCKER_USERNAME
   * DOCKER_PASSWORD
3. Go to https://github.com/<YOUR_GITHUB_ORG>/hto/actions 
4. Click the workflow you want to execute on the left hand side
5. Click the "Run Workflow" button on the right side and select "main"

## Contributing

Bug reports and pull requests are welcome on GitHub at https://github.com/saucelabs-training/hto. 

## License

The code is available as open source under the terms of the [MIT License](https://opensource.org/licenses/MIT).
