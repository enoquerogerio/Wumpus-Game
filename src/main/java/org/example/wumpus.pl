% wumpus.pl

% Representação do ambiente
% sala(X, Y, Ouro, Wumpus, Poço)
sala(1, 1, 0, 0, 0).    % Sala inicial
sala(1, 2, 0, 0, 1).    % Poço
sala(1, 3, 0, 0, 0).
sala(1, 4, 0, 0, 0).
sala(2, 1, 1, 0, 0).    % Ouro
sala(2, 2, 0, 1, 0).    % Wumpus
sala(2, 3, 0, 0, 1).    % Poço
sala(2, 4, 0, 0, 0).
sala(3, 1, 0, 0, 0).
sala(3, 2, 0, 0, 0).
sala(3, 3, 0, 0, 0).
sala(3, 4, 0, 0, 1).    % Poço
sala(4, 1, 0, 0, 0).
sala(4, 2, 0, 0, 0).
sala(4, 3, 0, 0, 0).
sala(4, 4, 0, 0, 0).    % Sala final

% Regras para detectar perigos (Wumpus e Poço)
perigo(X, Y) :- sala(X, Y, _, 1, _). % Wumpus
perigo(X, Y) :- sala(X, Y, _, _, 1). % Poço

% Sensores
brisa(X, Y) :- sala(PocoX, PocoY, 1, _, _), (PocoX is X + 1, Y = PocoY ; PocoX is X - 1, Y = PocoY ; X = PocoX, Y is PocoY + 1 ; X = PocoX, Y is PocoY - 1), dentro_limites(X, Y). % Verifica se está dentro dos limites

fedor(X, Y) :- sala(WumpusX, WumpusY, _, 1, _), (WumpusX is X + 1, Y = WumpusY ; WumpusX is X - 1, Y = WumpusY ; X = WumpusX, Y is WumpusY + 1 ; X = WumpusX, Y is WumpusY - 1),  dentro_limites(X, Y). % Verifica se está dentro dos limites


brilho(X, Y) :- sala(X, Y, 1, _, _). % Ouro

% Regras de movimento
pode_ir(X1, Y1, X2, Y2) :- X2 is X1 + 1, Y2 is Y1, dentro_limites(X2, Y2).
pode_ir(X1, Y1, X2, Y2) :- X2 is X1 - 1, Y2 is Y1, dentro_limites(X2, Y2).
pode_ir(X1, Y1, X2, Y2) :- X2 is X1, Y2 is Y1 + 1, dentro_limites(X2, Y2).
pode_ir(X1, Y1, X2, Y2) :- X2 is X1, Y2 is Y1 - 1, dentro_limites(X2, Y2).

% Limites 4x4
dentro_limites(X, Y) :- X >= 1, X =< 4, Y >= 1, Y =< 4.


% Regras para disparar a flecha
disparar_flecha(X, Y) :- sala(WumpusX, WumpusY, _, 1, _), (X is WumpusX + 1, Y = WumpusY ; X is WumpusX - 1, Y = WumpusY ; X = WumpusX, Y is WumpusY + 1 ; X = WumpusX, Y is WumpusY - 1), dentro_limites(X, Y).  % Verifica se a posição (X,Y) está dentro dos limites  mapa

% Matar o Wumpus se o jogador acertar com a flecha
matar_wumpus(X, Y) :- sala(WumpusX, WumpusY, _, 1, _), X = WumpusX, Y = WumpusY. % Se o jogador acertar a posição Wumpus
