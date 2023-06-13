import extensions.CSVFile;

class Scam extends Program {

	final String UNMOVABLE_COLOR = "\033[48;2;47;131;222m";
	final String ARROW_COLOR = "\033[48;2;204;66;66m";
	final String PATTERN_COLOR = "\033[48;2;216;216;35m";
	final String ROTATE_COLOR = "\033[48;2;213;127;56m";
	final String MOVABLE_COLOR = "\033[48;2;20;20;110m";
	final String VIRUS_COLOR = "\033[48;2;136;2;102m";

	final String RESET_COLOR = "\u001B[0m";
	final String EMPTY_COLOR = "\033[48;2;105;208;216m";
	final String BLACK_COLOR = "\033[48;2;0;0;0m";
	final String ZONE_COLOR = "\033[48;2;11;202;46m";
	final String WIN_COLOR = "\u001B[47m";

	final String RED = "\033[38;2;235;80;80m";
	final String GREEN = "\033[38;2;100;235;100m";

	final int SIZE = length(getAllFilesFromDirectory("../ressources/Levels")) - 1;

	String[] color = new String[] { PATTERN_COLOR, UNMOVABLE_COLOR, ARROW_COLOR,
			MOVABLE_COLOR, ROTATE_COLOR, VIRUS_COLOR, RESET_COLOR};
	final char[] everyCubes = new char[] { 'R', 'D', 'L',
			'U', 'X', '@' };

	void showLevel(Level lvl) { //affiche le tableau complet avec les couleurs
		bigSpace();
		print("  ");
		for (int i = 0; i < length(lvl.tab, 2); i++) {
			print(" " + (char) ('A' + i));
		}
		println();
		for (int i = 0; i < length(lvl.tab, 1); i++) {
			if (i < 9) {
				print(i + 1 + "  ");
			} else {
				print(i + 1 + " ");
			}
			for (int j = 0; j < length(lvl.tab, 2); j++) {
				if (!isNull(lvl.tab[i][j])) {
					print(color[lvl.tab[i][j].type] + lvl.tab[i][j].symbol + " " + RESET_COLOR);
				} else if (lvl.placeableZone[i][j]) {
					print(ZONE_COLOR + "  " + RESET_COLOR);
				} else if (lvl.motif[i][j]) {
					print(WIN_COLOR + "  " + RESET_COLOR);
				} else {
					print(EMPTY_COLOR + "  " + RESET_COLOR);
				}
			}
			println();
		}
		println("--------------------");
	}

	boolean isNull(Cube c) { 
		return c == null;
	}

	boolean isOpposite(Cube basecube, Direction d) { //vérifie si les directions des cubes sont opposées
		boolean value = false;
		if (!isNull(basecube)) {
			Direction c1 = basecube.direction;
			if ((c1 == Direction.RIGHT && d == Direction.LEFT) ^ (c1 == Direction.DOWN && d == Direction.UP) ^ (c1 == Direction.LEFT && d == Direction.RIGHT) ^ (c1 == Direction.UP && d == Direction.DOWN)) {
				value = true;
			}
		}
		return value;
	}

	boolean isValid(Level lvl, int i, int j) { //vérifie si la position donnée est valide (indices valides dans le tableau)
		return (i >= 0) && (i < length(lvl.tab, 1)) && (j >= 0) && (j < length(lvl.tab, 2));
	}

	boolean updateCube(Level lvl, int l, int c, Direction basedir) { // vérifie la direction du block pour le déplacer ou effectuer une action + récursivité si case suivante non nulle
		boolean value = false;
		if (!isNull(lvl.tab[l][c]) && !lvl.tab[l][c].moved && !(lvl.tab[l][c].type == 1) && !(lvl.tab[l][c].type == 6)) {
			if (lvl.tab[l][c].type == 5) {
				virusCube(lvl, l, c);
			}

			if (basedir == Direction.NONE && lvl.tab[l][c].type == 4) {
				applyRotation(lvl, l, c);
			}

			if (basedir == Direction.RIGHT && (c + 1 < length(lvl.tab, 2)) && ((isNull(lvl.tab[l][c + 1])) || (!isOpposite(lvl.tab[l][c+1],basedir) && updateCube(lvl, l, c + 1, basedir)))) {
				value = cubeMove(lvl, l, c, 0, 1, basedir);
			}
			else if (basedir == Direction.LEFT && (c - 1 >= 0) && ((isNull(lvl.tab[l][c - 1])) || (!isOpposite(lvl.tab[l][c-1],basedir) && updateCube(lvl, l, c - 1, basedir)))) {
				value = cubeMove(lvl, l, c, 0, -1, basedir);
			}
			else if (basedir == Direction.UP && (l - 1 >= 0) && ((isNull(lvl.tab[l - 1][c])) || (!isOpposite(lvl.tab[l-1][c],basedir) && updateCube(lvl, l - 1, c, basedir)))) {
				value = cubeMove(lvl, l, c, -1, 0, basedir);
			}
			else if (basedir == Direction.DOWN && (l + 1 < length(lvl.tab, 1)) && ((isNull(lvl.tab[l + 1][c])) || (!isOpposite(lvl.tab[l+1][c],basedir) && updateCube(lvl, l + 1, c, basedir)))) {
				value = cubeMove(lvl, l, c, 1, 0, basedir);
			}


		}
		return value;
	}

	boolean cubeMove(Level lvl, int l, int c, int i, int j, Direction basedir) { //déplace le cube donné
		lvl.tab[l][c].moved = true;
		if (lvl.tab[l][c].direction != basedir) {
			lvl.tab[l][c].moved = false;
		}
		lvl.tab[l + i][c + j] = lvl.tab[l][c];
		lvl.tab[l][c] = null;
		return true;
	}

	void globalMove(Level lvl) { //applique le déplacement à tous les cubes du plateau
		for (int i = 0; i < length(lvl.tab, 1); i++) {
			for (int j = 0; j < length(lvl.tab, 2); j++) {
				if (!isNull(lvl.tab[i][j])) {
					updateCube(lvl, i, j, lvl.tab[i][j].direction);
				}
			}
		}
	}

	void resetMoved(Level lvl) { // reset du boolean moved qui sert à empecher les cubes de se déplacer éternellement à chaque tour
		for (int i = 0; i < length(lvl.tab, 1); i++) {
			for (int j = 0; j < length(lvl.tab, 2); j++) {
				if (lvl.tab[i][j] != null) {
					lvl.tab[i][j].moved = false;
				}
			}
		}
	}

	boolean patternDone(Level lvl) { // vérifie si les zones motif sont remplies
		for (int i = 0; i < length(lvl.tab, 1); i++) {
			for (int j = 0; j < length(lvl.tab, 2); j++) {
				if (lvl.motif[i][j] && (lvl.tab[i][j] == null || lvl.tab[i][j].type != 0)) {
					return false;
				}
			}
		}
		return true;
	}

	Cube createCube(int type, Direction direct, char symbole) {
		Cube c = new Cube();
		c.type = type;
		c.direction = direct;
		c.symbol = symbole;
		return c;
	}

	Level createLevel(int l, int c, int moves, int placements) {
		Level lvl = new Level();
		lvl.tab = new Cube[l][c];
		lvl.placeableZone = new boolean[l][c];
		lvl.motif = new boolean[l][c];
		return lvl;
	}

	Level getLevelFromFile(int i) { //récupère un fichier, sa hauteur et sa largeur et le renvoie en type level
		String str = turnIntToLevelStr(i);
		int l = 1;
		int c = 0;
		extensions.File f = newFile(str);
		c = length(readLine(f));
		while (ready(f)) {
			l++;
			readLine(f);
		}
		return initializeLevelWithFile(l, c, newFile(str));
	}

	Level initializeLevelWithFile(int l, int c, extensions.File f) { //place les cubes grace au caractère via la fonction turnCharToCube
		String line;
		char car;
		Level lvl = createLevel(l, c, 0, 0);
		for (int i = 0; i < l; i++) {
			line = readLine(f);
			for (int j = 0; j < c; j++) {
				car = charAt(line, j);
				if (car == '+') {
					lvl.motif[i][j] = true;
				} else if (car == '*') {
					lvl.placeableZone[i][j] = true;
				} else {
					lvl.tab[i][j] = turnCharToCube(car);
				}
			}
		}
		return lvl;
	}

	Cube turnCharToCube(char c) { //transforme chaque caractère en cube
		Cube cube;
		if (c == 'M') {
			cube = createCube(0, Direction.NONE, 'Ω');
		} else if (c == '#') {
			cube = createCube(1, Direction.NONE, '×');
		} else if (c == 'R') {
			cube = createCube(2, Direction.RIGHT, '→');
		} else if (c == 'D') {
			cube = createCube(2, Direction.DOWN, '↓');
		} else if (c == 'L') {
			cube = createCube(2, Direction.LEFT, '←');
		} else if (c == 'U') {
			cube = createCube(2, Direction.UP, '↑');
		} else if (c == 'X') {
			cube = createCube(3, Direction.NONE, '·');
		} else if (c == '@') {
			cube = createCube(4, Direction.NONE, '↺');
		} else if (c == 'V') {
			cube = createCube(5, Direction.NONE, '⧖');
		} else if (c == '-') {
			cube = createCube(6, Direction.NONE, ' ');
		}else {
			cube = null;
		}
		return cube;
	}

	boolean forceLoad() {
		char c;
		String str = readString();
		if (length(str) == 1) {
			c = charAt(str, 0);
			if (c == '-') {
				return true;
			}
			if (c == '*') {
				System.exit(0);
			}
		}
		return false;
	}


	void virusCube(Level lvl, int i, int j) {
		if (isValid(lvl,i-1,j) && !isNull(lvl.tab[i-1][j]) && !(lvl.tab[i-1][j].type == 5) && lvl.tab[i-1][j].type != 6) {
			lvl.tab[i-1][j] = turnCharToCube('V');
			virusCube(lvl, i-1, j);
			waitFor(20);
		}
		else if (isValid(lvl,i+1,j) && !isNull(lvl.tab[i+1][j]) && !(lvl.tab[i+1][j].type == 5) && lvl.tab[i+1][j].type != 6) {
			lvl.tab[i+1][j] = turnCharToCube('V');
			virusCube(lvl, i+1, j);
			waitFor(20);
		}
		else if (isValid(lvl,i,j-1) && !isNull(lvl.tab[i][j-1]) && !(lvl.tab[i][j-1].type == 5) && lvl.tab[i][j-1].type != 6) {
			lvl.tab[i][j-1] = turnCharToCube('V');
			virusCube(lvl, i, j-1);
			waitFor(20);
		}
		else if (isValid(lvl,i,j+1) && !isNull(lvl.tab[i][j+1]) && !(lvl.tab[i][j+1].type == 5) && lvl.tab[i][j+1].type != 6) {
			lvl.tab[i][j+1] = turnCharToCube('V');
			virusCube(lvl, i, j+1);
			waitFor(20);
		}
	}

	int placeCube(int[] levelCubes, Level lvl, int placeable) { //La fonction permettant à l'utilisateur de placer un cube
		int l; int x; char c; String str;
		println();
		println("Tapez '-' pour forcer le lancement\nTapez '*' pour quitter le programme\nSinon appuyez sur entrée");
		if (forceLoad()) {
			return 0;
		}
		print("Choisis une colonne : ");
		str = readString();
		if (length(str) == 1) {
			c = (char) (charAt(str, 0) - 'A');
			print("Choisis une ligne : ");
			l = readInt() - 1;
			print("Choisis un cube à placer : ");
			x = readInt();
			if (isValid(lvl, l, c) && isNull(lvl.tab[l][c]) && lvl.placeableZone[l][c]
					&& haveThisCubeLeft(levelCubes, x)) {
				lvl.tab[l][c] = turnCharToCube(everyCubes[x]);
				levelCubes[x]--;
				placeable--;
			} else {
				println("Case invalide, réessaie !");
				placeCube(levelCubes, lvl, placeable);
			}
		}
		else {
			placeCube(levelCubes, lvl, placeable);
		}
		return placeable;
	}

	int[] getCubesFromFile(int i) { //récupère le nombre de cubes placables d'un fichier
		String str = turnIntToCubeStr(i);
		int l = 1;
		extensions.File f = newFile(str);
		while (ready(f)) {
			l++;
			readLine(f);
		}
		int[] tab = new int[l];
		return turnCubeFileToTab(tab, newFile(str));
	}

	int[] turnCubeFileToTab(int[] tab, extensions.File f) { //renvoie un tableau des cubes plaçables
		String line;
		int i = 0;
		while (ready(f)) {
			line = readLine(f);
			tab[i] = charAt(line, 0);
			i++;
		}
		return tab;
	}

	String turnIntToLevelStr(int i) {
		return "../ressources/Levels/Level" + i + "/Level.txt";
	}

	String turnIntToCubeStr(int i) {
		return "../ressources/Levels/Level" + i + "/Cubes.txt";
	}

	void showDefaultCubes(int[] levelCubes) { //montre nos cubes plaçables
		Cube c;
		for (int j = 0; j < length(everyCubes); j++) {
			if (levelCubes[j] != '0') {
				c = turnCharToCube(everyCubes[j]);
				print("(" + j + " | " + (char) levelCubes[j] + "x" + color[c.type] + c.symbol + " " + RESET_COLOR + ")  ");
			}
		}
		println();
	}

	Direction cubeRotation(Direction d) {
		if (d == Direction.RIGHT) return Direction.DOWN;
		if (d == Direction.DOWN) return Direction.LEFT;
		if (d == Direction.LEFT) return Direction.UP;
		if (d == Direction.UP) return Direction.RIGHT;
		return Direction.NONE;
	}

	char charRotation(char c) {
		if (c == '→') return'↓';
		if (c == '↓') return'←';
		if (c == '←') return '↑';
		if (c == '↑') return '→';
		return c;
	}

	void applyRotation(Level lvl, int i, int j) {
		if (isValid(lvl,i-1,j) && !isNull(lvl.tab[i-1][j])) {
			lvl.tab[i-1][j].direction = cubeRotation(lvl.tab[i-1][j].direction); 
			lvl.tab[i-1][j].symbol = charRotation(lvl.tab[i-1][j].symbol);}
		if (isValid(lvl,i+1,j) && !isNull(lvl.tab[i+1][j])) {
			lvl.tab[i+1][j].direction = cubeRotation(lvl.tab[i+1][j].direction); 
			lvl.tab[i+1][j].symbol = charRotation(lvl.tab[i+1][j].symbol);}
		if (isValid(lvl,i,j-1) && !isNull(lvl.tab[i][j-1])) {
			lvl.tab[i][j-1].direction = cubeRotation(lvl.tab[i][j-1].direction); 
			lvl.tab[i][j-1].symbol = charRotation(lvl.tab[i][j-1].symbol);}
		if (isValid(lvl,i,j+1) && !isNull(lvl.tab[i][j+1])) {
			lvl.tab[i][j+1].direction = cubeRotation(lvl.tab[i][j+1].direction); 
			lvl.tab[i][j+1].symbol = charRotation(lvl.tab[i][j+1].symbol);}
	}

	boolean haveThisCubeLeft(int[] levelCubes, int i) { //vérifie s'il y a encore un de ces cubes dans nos cubes plaçables
		return levelCubes[i] != '0';
	}

	void bigSpace() { //fait un gros espace
		for (int i = 0; i < 69; i++) {
			print("\n");
		}
	}

	boolean startLevel(int lvlID, int placeable, int moves) { //Lance le niveau
		int[] lvlcubes = getCubesFromFile(lvlID);
		Level lvl = getLevelFromFile(lvlID);
		waitFor(2000);
		while (placeable != 0) {
			showLevel(lvl);
			showDefaultCubes(lvlcubes);
			numberOfMoves(moves);
			println("Vous pouvez encore placer " + placeable + " cube(s).");
			placeable = placeCube(lvlcubes, lvl, placeable);
		}
		showLevel(lvl);
		numberOfMoves(moves);
		return simulateLevel(lvl, moves);
	}

	void numberOfMoves(int moves) { //Compte les déplacements
		if (moves > 1) {
			println("Il reste encore " + moves + " déplacements !");
		} else if (moves == 1) {
			println("Il reste encore 1 déplacement !");
		} else if (moves == 0) {
			println("Il ne reste aucun déplacement ! /!\\ ");
		} else {
			println("Ce niveau n'a pas de limite de déplacement.");
		}
	}

	void waitFor(int i) { //fonction de délai
		long start = getTime();
		long t = 0;
		while (t - start != i) {
			t = getTime();
		}
	}

	boolean simulateLevel(Level lvl, int moves) { //lance la simulation à la manière du jeu de conway
		boolean value = true;
		while ((!patternDone(lvl) && (moves > 0 || moves <= -1))) {
			waitFor(250);
			globalMove(lvl);
			moves--;
			showLevel(lvl);
			numberOfMoves(moves);
			resetMoved(lvl);
		}
		if (patternDone(lvl)) {
			println("BRAVO !!!!");
		} else {
			println("T'es éclaté. (comme mon code mais c'est un secret d'état)");
			value = false;
		}
		waitFor(1000);
		return value;
	}

	void playLevel(int lvlID) { // Lance toutes les fonctions du jeu
		bigSpace();
		CSVFile csv = loadCSV("../ressources/Levels/infos.csv", ';');
		int placeable = stringToInt(getCell(csv, lvlID, 1));
		int moves = stringToInt(getCell(csv, lvlID, 2));
		println("Niveau " + lvlID);
		boolean game = true;
		while (game) {
			game = !(startLevel(lvlID, placeable, moves));
		}
		game = false;
	}

	void wannaStop(int lvl, int save) {
		println("Souhaitez-vous arrêter de jouer ? (y/n)");
		char c = readChar();
		storeSave(lvl, save);
		if (c == 'y') {
			System.exit(0);
		}
	}

	void tutorialText(String txt) {
		bigSpace();
		println(txt);
		String s = readString();
	}

	int chooseLevel(int lvlID) {
		int i;
		showLevel(getLevelFromFile(lvlID));
		do {
			println("1. Niveau Précédent\n2. Niveau Suivant\n3. Choisir ce niveau");
			i = readInt();
			if (i == 1 && lvlID > 0) {
				lvlID--;
			}
			else if (i == 2 && lvlID < SIZE-1) {
				lvlID++;
			}
			showLevel(getLevelFromFile(lvlID));
		} while (i != 3);
		return lvlID;
	}

	int[] loadSave() {
		CSVFile csv = loadCSV("../ressources/saves.csv", ',');
		char c;
		int i; int lvl = 0; int save = 0;
		bigSpace();
		print(RED);
		println("Choisir une sauvegarde :");
		print(GREEN);
		if (stringToInt(getCell(csv, 0, 1)) != SIZE) {
			println("--------\nSauvegarde 1 : Niveau " + getCell(csv, 0, 1));
		}
		else {
			println("--------\nSauvegarde 1 : Jeu terminé");
		}
		if (stringToInt(getCell(csv, 1, 1)) != SIZE) {
			println("--------\nSauvegarde 2 : Niveau " + getCell(csv, 1, 1));
		}
		else {
			println("--------\nSauvegarde 2 : Jeu terminé");
		}
		if (stringToInt(getCell(csv, 2, 1)) != SIZE) {
			println("--------\nSauvegarde 3 : Niveau " + getCell(csv, 2, 1));
		}
		else {
			println("--------\nSauvegarde 3 : Jeu terminé");
		}
		print(RESET_COLOR);
		do {
			i = readInt();
		} while (!(i >= 0 && i <= 3));
		
		if (i == 1) {
			save = 1;
			lvl = stringToInt(getCell(csv, 0, 1));
		}
		else if (i == 2) {
			save = 2;
			lvl = stringToInt(getCell(csv, 1, 1));
		}
		else if (i == 3) {
			save = 3;
			lvl = stringToInt(getCell(csv, 2, 1));
		}
		if (lvl == SIZE) {
			println("Souhaitez-vous recommencer depuis le début ? (y/n)");
			c = readChar();
			if (c == 'y') {
				lvl = 0;
			}
			else {
				println("Dans ce cas là, choisissez un niveau");
				waitFor(1000);
				lvl = chooseLevel(SIZE-1);
			}
		}
		println("Voulez-vous (re)faire le tutoriel ? (y/n)");
		c = readChar();
		if (c == 'y') {
			initTutorial();
		}
		return new int[]{lvl, save};
	}
	

	void storeSave(int lvl, int save) {
		CSVFile csv = loadCSV("../ressources/saves.csv", ',');
		int l = rowCount(csv);
		int c = columnCount(csv);
		String[][] tab = new String[l][c];
		for (int i = 0 ; i < l ; i++) {
			for (int j = 0 ; j < c ; j++) {
				tab[i][j] = getCell(csv, i, j);
			}
		}
		tab[save-1][1] = lvl + "";
		saveCSV(tab, "../ressources/saves.csv");
	}

	void initTutorial() {
		tutorialText("Bonjour !");
		tutorialText("Ce jeu ludo-pédagogique a un principe très simple :\n- Déplacer les blocs motifs " + PATTERN_COLOR + "Ω " + RESET_COLOR + " sur les cases motifs " + WIN_COLOR + "  " + RESET_COLOR + ".");
		tutorialText("Pour vous aider, vous aurez des blocs spéciaux qui permettront le déplacement des blocs motifs.");
		tutorialText("La liste des blocs vous sera affichée en début de chaque niveau");
		tutorialText("À chaque tour vous pourrez placer un certain nombre de blocs dans la zone verte (uniquement).");
		tutorialText("Premièrement nous avons les blocs " + ARROW_COLOR + "→ ↓ ← ↑ " + RESET_COLOR + " qui se déplacement orthogonalement et déplacent d'autres blocs.");
		tutorialText("Ensuite, nous avons les blocs " + UNMOVABLE_COLOR + "× " + RESET_COLOR + " qui ne peuvent pas être déplacés et stoppent tout déplacement.");
		tutorialText("Il y a également les blocs " + ROTATE_COLOR + "↺ " + RESET_COLOR + " qui changent la direction des blocs qui les touchent.");
		tutorialText("Poursuivons avec les blocs " + MOVABLE_COLOR + "· " + RESET_COLOR + " qui n'ont aucune spécificité, simplement ils sont déplaçables.");
		tutorialText("Et pour terminer, nous avons les blocs " + VIRUS_COLOR + "⧖ " + RESET_COLOR + " qui contaminent tout bloc les touchant.");
		tutorialText("Prenez garde ! Certains niveaux ont un nombre limité de mouvements avant leur fin inévitable, bon courage ! ;)");
	}

	void algorithm() {
		int[] tab = loadSave();
		int lvl = tab[0];
		int save = tab[1];
		println("Commencez en appuyant sur entrée.");
		String str = readString();
		if (equals(str, "KONAMI")) {
			lvl = readInt();
		}
		for (int j = lvl ; j < SIZE ; j++) {
			playLevel(j);
			lvl++;
			wannaStop(lvl, save);
		}

		tutorialText("Vous avez terminé ce jeu ! Bravo !");
	}
}