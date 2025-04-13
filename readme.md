**Podman Command to run the Chroma DB for vector Store**

podman run -it --rm --name chroma -p 8000:8000 ghcr.io/chroma-core/chroma:0.5.20

**Commands to run LLM Model locally Ex:-**

brew install ollama
(Also you can try with python3/python pip install)
ollama run gemma:2b

!Must-Try
ollama run llama3.2