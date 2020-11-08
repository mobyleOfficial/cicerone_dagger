# Cicerone with Dagger 2
This repository contains a project that shows how to use Cicerone to navigate between Fragments, and Dagger2 to dependency injection.

## Libraries
- [Cicerone](https://github.com/terrakok/Cicerone)
- [Dagger2](https://github.com/google/dagger)

## How does it works?
![alt text](https://i.imgur.com/ZM0SIYJ.png)

As shown in the image, there're 2 containers, one inside the Activity (contains the Bottom Navigation Bar), and other to the Fragment responsable to manage the apps flow. Everytime the user change tabs, the fragments visibility is changed. Each tab has it's own Cicerone instance (injected using Dagger2)

## References
[Base Article](https://medium.com/@yurimachioni/creating-an-instagram-like-flow-using-cicerone-and-dagger2-bottomnavigation-with-fragments-777771ff4401)

## Author
Bruno Abe

## License
[MIT](https://choosealicense.com/licenses/mit/)
