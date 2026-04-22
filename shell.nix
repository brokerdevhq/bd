{ pkgs ? import <nixpkgs> { } }:

pkgs.mkShell {
  buildInputs = with pkgs; [
    openjdk25
    clojure
    babashka
    polylith
    gh
  ];
}
