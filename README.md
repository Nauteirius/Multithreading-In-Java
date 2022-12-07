# Multithreading-In-Java
Świat powoli uwalnia się spod jarzma pandemii. Pewna nadmorska metropolia w swoim porcie zmaga się z ogromnym ruchem zgromadzonym po miesiącach lockdown'u. Twoim zadaniem jest optymalne i jednocześnie bezpieczne zarządzanie ruchem morskim w tym porcie. Zadanie jednak nie jest takie proste. Port ma ograniczoną liczbę doków N, do których cumować mogą statki o różnych wielkościach K (K <= N). Każdy statek cumując zajmuje więc K następujących po sobie doków. Na domiar złego jedyna droga do portu prowadzi przez kanał, w którym może znajdować się maksymalnie M statków jednocześnie, niezależnie od ich rozmiaru. Orientacyjny rysunek dla N = 5 i M = 3 został przedstawiony:
![obraz](https://user-images.githubusercontent.com/97364999/206308353-59088f7b-83a8-4e95-beae-110835831a3c.png)

Rysunek poglądowy zadania

Napisz klasę implementującą interfejs SeaportManager o nazwie SeaportManagerImpl, która będzie właściwie zarządzała ruchem w porcie. Osiągnij to poprzez odpowiednie blokowanie wątku w metodach z interfejsu SeaportManager. Każdy statek zaimplementowany jest jako wątek, który wywołuje kolejno metody:

    requestSeawayEntrance() - wywoływana przy zbliżaniu się do kanału; statek zacznie wpływać do kanału po powrocie z tej metody.
    requestPortEntrance() - wywoływana przy zbliżaniu się do portu; statek zacznie wpływać do portu po powrocie z tej metody; liczba całkowita zwrócona przez tą metodę określa najniższy numer doku spośród doków, do których statek zacumuje.
    signalPortEntered() - wywoływana po opuszczeniu kanału (podczas wpływania do portu).
    requestPortExit() - wywoływana przed opuszczeniem portu; statek zacznie wpływać do kanału po powrocie z tej metody.
    signalPortExited() - wywoływana po opuszczeniu portu.
    signalShipSailedAway() - wywoływana tuż po opuszczeniu kanału (podczas wypływania na otwarte morze).

Twój program musi spełnić następujące warunki:

    W kanale może się znaleźć maksymalnie M statków jednocześnie.
    Dany dok może być zajmowany przez maksymalnie jeden statek jednocześnie.
    Numer najniższego doku, do którego statek ma zacumować, musi zawierać się w poprawnym zakresie.
    Zasoby muszą być wykorzystywane optymalnie (równoległość implementacji).
    Każdy statek musi zakończyć swoją podróż (brak zakleszczeń).

Dodatkowe informacje:

    Jako pierwsza zostanie wywołana metoda init() poprzez którą Twoja klasa otrzyma parametry portu (liczba doków, pojemność kanału).
    Metoda requestPortEntrance() zwraca numer wyznaczający doki, do których statek zacumuje. Jest to najniższy numer doku spośród doków, do których statek zacumuje. Doki są numerowane od zera. Przykładowo, jeśli rozmiar statku wynosi k = 3 i metoda zwróci liczbę 1, to statek zacumuje do doków numer 1, 2 oraz 3. Statek będzie pamiętał ten numer po przypisaniu i możesz go sprawdzić metodą getAssignedDock() interfejsu Ship.
    Rozmiar statku sprawdzisz metodą getDockingSize() interfejsu Ship.
    Nie ma żadnych innych gwarancji co do wątków poza kolejnością wywołań metod - każdy wątek może dowolnie dobierać czas pomiędzy wspomnianymi wywołaniami.

Definicje interfejsów: SeaportManager oraz Ship. 

![obraz](https://user-images.githubusercontent.com/97364999/206308180-92a1792f-3eba-4b76-ba2a-d602a845274b.png)
![obraz](https://user-images.githubusercontent.com/97364999/206308206-c9e58d13-4068-422a-8aaa-a8bb0c2aa3b7.png)

