with (import <nixpkgs> {});
mkShell
{
  buildInputs =
  [
    leiningen
    openjdk
    nodejs
  ];
  shellHook =
  ''
  '';
}
