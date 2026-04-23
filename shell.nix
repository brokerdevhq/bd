{ pkgs ? import <nixpkgs> { } }:

pkgs.mkShell {
  buildInputs = with pkgs; [
    babashka
    clojure
    gh
    nodejs
    openjdk25
    polylith
  ];
}
