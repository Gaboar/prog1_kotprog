# Heroes of Might & Magic (kötprog)

### 1. Futtatás

Javaslom, hogy nyisd meg projectet [IntelliJ IDEA](https://www.jetbrains.com/idea/) ban. Ott nagy valószínűséggel működni fog.

### 2. Futtatás

Futtatáshoz szükséged lesz [Maven](https://maven.apache.org/download.cgi) re.
Miután letöltötted és kicsomagoltad a neked megfelelőt, add hozzá a mappáját a PAHT-hoz.
Ezt ellenőrízni az `mvn -v` paranccsal tudod, ha sikerült, kiírja a verziót.
Nekem csak az `mvn clean javafx:run` paranccsal sikerült működésre bírni.

### 3. Használat

A játék elindítása után két mód közül választhatunk.
Egyjátékos módban a gép, többjátékos módban egy másik játékos ellen tudunk játszani. Ezeken belül a módok csak a kezdő pénzt módosítják.
A bolt működése szerintem egyértelmű. Ha a szövegekre mutatsz információkat ír ki a különböző vásárolható dolgokról.  
Az egységek lerakásánál a kiemelt színű mezőkre kattintva lehet letenni az egységet.
A lerakott egységek pozicióját lehet is lehet változtatni.  
Csata közben a zöld egységre kattintva lehet kiválasztani, ezután megjelennek a lehetséges lépések sárgával.
Ha az egységnek van lehetősége támadni akkor ezt piros kijelöléssel jelzi a játék.
A csata közben az egyérrel egységekre, hősökre vagy varázslatokra mutatva tudhatsz meg róluk információt.
A játék akkor ér véget, ha az egyik hősnek elfogy az összes katonája.

### 4. Info

Belépési pont com.progegy.kotprog packageben a Main classban van.

