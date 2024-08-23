package main.gian.App.Contracts.Infrastructure.Repository;

public interface RepositoryInterface {
    public void sendMessage(String message);
    public void close();
}