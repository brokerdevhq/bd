# bd
The BrokerDev open-source repo.

## Prerequisites

### With Nix (recommended)
[Nix](https://nixos.org/) is a declarative package manager that ensures reproducible development environments. Using our `shell.nix`, you get the exact same dependencies we use:

```bash
nix-shell
```

### Without Nix
We keep dependencies minimal. Install the following:

- [Clojure](https://clojure.org/guides/install_clojure)
- [Babashka](https://github.com/babashka/babashka#installation)
- [Node.js](https://nodejs.org/)
- [OpenJDK 25](https://openjdk.org/projects/jdk/25/)
- [Polylith CLI](https://github.com/polyfy/polylith)
- [GitHub CLI](https://cli.github.com/) (optional)

## Polylith
This project uses the [Polylith](https://polylith.gitbook.io/polylith) architecture. See the official documentation for concepts and tooling.

## Projects
- **api** - Backend services
- **web** - Serves the SPA bundle and static files
- **spa** - The main user-facing application, built as a single-page app with ClojureScript

## Development
Start a development REPL:

```bash
clj -M:dev:nrepl
```

For frontend ClojureScript development (SPA):

```bash
clj -M:cljs watch spa
```

## Build
Build a backend uberjar:

```bash
clj -T:build uberjar :project web :version "1.0.0"
```

The jar will be output to `projects/<project>/target/`.

Build the frontend for production:

```bash
clj -M:cljs release release
```

This outputs optimized JS to `bases/web/resources/web/public/app/js/`.

## Contribution
We're currently not accepting contributions, but feel free to get in touch if you want to discuss something you need or want.
